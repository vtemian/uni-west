cmake_minimum_required(VERSION 2.8.4)
project(revers_list)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(revers_list ${SOURCE_FILES})