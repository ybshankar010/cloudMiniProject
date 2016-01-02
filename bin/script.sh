#!/bin/bash

machines=$1
images=$2
types=$3
ip="10.3.3.199"
port=80
#copying images to the machines available
while read image_path
do 
	echo $image_path
		filename=`basename $image_path`
		echo $filename
		rsync $image_path "./"$filename
	while read machine
	do
		echo $machine
		echo $machine":/var/lib/libvirt/"$filename
		rsync $filename $machine":/var/lib/libvirt/images/"
	done < $machines
	rm -f "./"$filename
done < $images
echo "Image files copied copied to the machine"
wget "http://apache.techartifact.com/mirror/cxf/2.6.2/apache-cxf-2.6.2.tar.gz"
tar -zxvf './apache-cxf-2.6.2.tar.gz'

#setting the Java class path
libvirt_path=`ls $HOME/libvirt*/target/libvirt*.jar`
classpath=".:/usr/share/java/jna.jar:$libvirt_path"
for jarpath in `ls apache-cxf-2.6.2/lib/*.jar`
        do
                      classpath=$classpath":./"$jarpath
        done 
wget "http://json-simple.googlecode.com/files/json-simple-1.1.1.jar"
classpath=$classpath":""./json-simple-1.1.1.jar"
echo $classpath
export CLASSPATH=$classpath

#runing the java file
java Server $ip $machines $images $types $port

