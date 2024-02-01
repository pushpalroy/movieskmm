#!/bin/sh
rm -rf .idea
./gradlew clean
rm -rf .gradle
rm -rf build
# shellcheck disable=SC2035
rm -rf */build
rm -rf iosApp/iosApp.xcworkspace
rm -rf iosApp/Pods
rm -rf iosApp/iosApp.xcodeproj/project.xcworkspace
rm -rf iosApp/iosApp.xcodeproj/xcuserdata