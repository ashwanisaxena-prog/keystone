#FROM apache/camel-k-runtime-jvm:2.7.0
FROM apache/camel-k:2.7.0-21-jdk


# Copy your local Maven repository into the image
#COPY .m2/repository /tmp/artifacts/m2

# (Optional) if you only want to copy specific jars
 COPY target/keystone-0-jar-with-dependencies.jar /etc/maven/m2/repository/ch/keystone/keystone/0/keystone-0.jar