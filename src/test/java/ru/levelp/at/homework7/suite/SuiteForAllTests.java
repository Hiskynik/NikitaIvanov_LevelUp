package ru.levelp.at.homework7.suite;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags({"mail", "delete", "topic", "fail"})
@SelectPackages("ru.levelp.at.homework7")
@IncludeClassNamePatterns(".*IT")
class SuiteForAllTests {
}
