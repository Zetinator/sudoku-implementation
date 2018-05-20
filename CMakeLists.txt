cmake_minimum_required (VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

project (Sudoku)

set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.6" "-target" "1.6")

add_jar(Sudoku src/Sudoku.java)

get_target_property(_jarFile Sudoku JAR_FILE)
get_target_property(_classDir Sudoku CLASSDIR)

message(STATUS "Jar file ${_jarFile}")
message(STATUS "Class compiled to ${_classDir}")

# # add_jar(target_name ../src/Sudoku.java)
# add_jar(Sudoku Sudoku.java
# 	OUTPUT_NAME Sudoku.jar
# 	OUTPUT_DIR /bin)

# enable_testing()
# add_test(NAME TestSudoku COMMAND ${Java_JAVA_EXECUTABLE} -cp ${_jarFile} Sudoku)
