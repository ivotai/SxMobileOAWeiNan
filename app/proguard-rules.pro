# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# okhttp
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# retrofit
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature

# Retain service method Parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

#model
-keep class com.unicorn.sxmobileoa.app.network.model.**{*;}
-keep class com.unicorn.sxmobileoa.app.mess.model.**{*;}
-keep class com.unicorn.sxmobileoa.app.ui.page.model.**{*;}
-keep class com.unicorn.sxmobileoa.login.model.**{*;}
-keep class com.unicorn.sxmobileoa.select.code.model.**{*;}
-keep class com.unicorn.sxmobileoa.select.dept.model.**{*;}
-keep class com.unicorn.sxmobileoa.simple.court.model.**{*;}
-keep class com.unicorn.sxmobileoa.simple.dbxx.model.**{*;}
-keep class com.unicorn.sxmobileoa.simple.main.model.**{*;}
-keep class com.unicorn.sxmobileoa.spd.model.**{*;}
-keep class com.unicorn.sxmobileoa.spdNext.model.**{*;}

#RxCache
#-dontwarn io.rx_cache2.internal.**
#-keepclassmembers enum io.rx_cache2.Source { *; }
#-keepclassmembernames class * { @io.rx_cache2.* <methods>; }

# xml may ok
# https://stackoverflow.com/questions/49936064/getting-error-while-building-signed-apk
-dontwarn org.xmlpull.v1.**
-dontnote org.xmlpull.v1.**
-keep class org.xmlpull.** { *; }
-keepclassmembers class org.xmlpull.** { *; }

-keep class org.simpleframework.xml.core.** { *; }
-keepclassmembers class org.simpleframework.xml.core.** { *; }

#dart
-dontwarn dart.internal.**
-keep class **__ExtraBinder { *; }
-keep class **__NavigationModelBinder { *; }
-keepclasseswithmembernames class * {
    @dart.* <fields>;
}
-keep class **Henson { *; }
-keep class **__IntentBuilder { *; }
-keep class **HensonNavigator { *; }

-dontwarn dart.common.**
