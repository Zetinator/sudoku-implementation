cmake_minimum_required (VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

enable_testing()

project (sudoku)

set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.6" "-target" "1.6")

add_jar(sudoku src/sudoku.java)

get_target_property(_jarFile sudoku JAR_FILE)
get_target_property(_classDir sudoku CLASSDIR)

message(STATUS "Jar file ${_jarFile}")
message(STATUS "Class compiled to ${_classDir}")

add_test(NAME TestSudoku COMMAND ${Java_JAVA_EXECUTABLE} -cp ${_jarFile} sudoku)
