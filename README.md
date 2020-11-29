# IssueReport
This library is a service provider for the [Pioneers IssueExtension](https://junit-pioneer.org/docs/issue/).
It consumes the data form the extension (a list of testcases, grouped by the issueId they are annotated with), combines it with a list of issue and check the executed tests if and how well the issues are covered.
At the end it will a report is generated and published.

For questions or feedback open [an issue](https://github.com/Bukama/IssueReport/issues) or contact me at [Twitter](https://twitter.com/bukamabish).

# Usage
This section covers how to use the library.
Some things are configurable - see [configuration](#configuration) section for further details.

## Dependency
As this is early development there's not deployment to Maven's central repository yet.
This said, you have to download the repo, build the project and install it into your local repository.

After that you can add the dependency to your POM:

```text
<groupId>com.github.bukama</groupId>
<artifactId>IssueReport</artifactId>
<version><!-- version you want to use --></version>
```

## Provide list of issues
To provide a list of issues just put a file called `issuelist.csv` (default) into the `src/test/resources` (default) folder.
The library will read it and extract the listed issues out of it.
You can configure the directory, name and format.

As of now only CSV format is supported, but more are planed.

### CSV
You can provide a list of issues by using a CSV-file.
As of now an issue must have three values (in the following order), separated by an `,`:

1. An id
2. A description
3. A priority value

Example:
`REQ-123,Description,HIGH`

The CSV-files are parsed using the [OpenCSV reader](http://opencsv.sourceforge.net/) library.
So the separating character is not configurable.

## Report
At the end of the test execution a report is created.
The report lists every issue provided by the issue list and extracted from the IssueExtension results.
For each issue a summary of the test results and the tests itself (including their name and result) are listed.
The report (`issueReport.xml`) is always published in XML-format using JAXB under `target/report` (default) .

_Further formats, e.g. HTML, are planed and will then be configurable._

# Configuration
Several values are configurable by system properties.
The following table shows the keys and default value (a `-` means no value is set).

| Key | Default value | Description |
| --- | --- | --- |
| com.github.bukama.ir.issuelist.directory | - | Subdirectory of the issue list. The directory will be prefixed with "." and the the value of File.separator, together e.g "./". |
| com.github.bukama.ir.issuelist.filename | issuelist | Filename of the issue list without extension or path |
| com.github.bukama.ir.issuelist.extension | csv | File extension of the issue list |
| com.github.bukama.ir.report.directory | target/reports | Directory where the report is generated (without trailing slash). The directory will be prefixed with "." and the the value of File.separator, together e.g "./". |



