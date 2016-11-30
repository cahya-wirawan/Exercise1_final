# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\FrosT\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Following 3 lines are enough actually:
#-dontwarn java.nio.file.**
#-dontwarn org.codehaus.**
#-dontwarn retrofit.**

-keep class com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keep interface com.squareup.okhttp.** { *; }

# for the interfaces
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

# If in your rest service interface you use methods with Callback argument.
-keepattributes Exceptions
# If your rest service methods throw custom exceptions, because you've defined an ErrorHan dler.
-keepattributes Signature
# Hide Warnings - these classes wont be used
-dontwarn okio.**
-dontwarn retrofit.**
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry