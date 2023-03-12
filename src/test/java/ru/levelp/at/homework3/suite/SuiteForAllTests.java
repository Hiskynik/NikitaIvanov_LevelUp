package ru.levelp.at.homework3.suite;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags({"mail", "delete", "topic"})
@SelectPackages("ru.levelp.at.homework3")
@IncludeClassNamePatterns(".*IT")
class SuiteForAllTests {
}
