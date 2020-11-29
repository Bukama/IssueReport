
package com.github.bukama.ir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.github.bukama.ir.config.Config;
import com.github.bukama.ir.jaxb.IssueReport;
import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.listreader.IssueListReader;
import com.github.bukama.ir.utils.IssueUtils;
import com.github.bukama.ir.utils.SummaryUtils;

import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueReportProcessor implements IssueProcessor {

  private static final Logger LOG = Logger.getAnonymousLogger();

  private static final IssueListReader ISSUE_LIST_READER = new IssueListReader();

  @Override
  public void processTestResults(List<IssueTestSuite> allIssueTestsSuites) {

    // Read issue list
    List<IssueType> allIssues = ISSUE_LIST_READER.readIssues();

    // Merge list, if theres something to merge
    allIssues = IssueUtils.mergeLists(allIssues, allIssueTestsSuites);

    // Create summaries
    allIssues = SummaryUtils.createSummaries(allIssues);

    // Marshall it a report
    writeReport(new IssueReport(allIssues));
  }

  /**
   * Writes the data to an {@link IssueReport} in XML-Format using JAXB..
   * 
   * @param report
   *          Report to marshal
   */
  void writeReport(IssueReport report) {

    try {
      // Create report file (delete first, if already exists)
      String fileName = buildReportFileName();
      Path xmlFile = Paths.get(fileName);
      Files.deleteIfExists(xmlFile);
      Files.createDirectories(xmlFile.getParent());
      Files.createFile(xmlFile);

      // Marshal with options
      JAXBContext jaxbContext = JAXBContext.newInstance(IssueReport.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      jaxbMarshaller.marshal(report, xmlFile.toFile());

      // Validate
      Source xmlFileSource = new StreamSource(xmlFile.toFile());
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      File xsdFile = new File(ClassLoader.getSystemResource("xsd/issueReport.xsd").toURI());
      Schema schema = schemaFactory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      validator.validate(xmlFileSource);
    } catch (Throwable t) {
      // Catch all to not break anything else, just because the report could not been created
      LOG.log(Level.WARNING, "Error while creating the pioneer report", t);
    }
  }

  /**
   * Creates the full file name of the report based on the system properties.
   *
   * @return Full file name
   */
  String buildReportFileName() {
    return "." + File.separator + Config.REPORT_DIRECTORY.asString() + File.separator + "issueReport.xml";
  }

}
