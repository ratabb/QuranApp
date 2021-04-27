#
-keepattributes SourceFile,LineNumberTable
#
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
# okHttp -> remove this when update to v4.10+
-dontwarn org.conscrypt.ConscryptHostnameVerifier
