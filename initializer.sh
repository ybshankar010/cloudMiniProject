#!/bin/bash

ipfile=$1					# This is the file that contains ip-addresses.
imagefile=$2					# This is the file that contains image-paths
types=$3					# This is the file that has types of virtual machines
ip="10.3.3.199"
port=80

while read imgpth					# The loop that is used to store the image from remote to current system.
do
	file=`basename $imgpth`
	echo `dirname $imgpth`
	rsync $imgpth "./"$file
	echo $file
	while read ip					# The loop that is uses to copy the file from local to remote system 
	do
		echo $ip":/var/lib/libvirt/"$file
		rsync $file $ip":/var/lib/libvirt/images/"	   
	done < $ipfile
	rm -f "./"$file					#removes the local image copied from remote system.
done < $imagefile

# Get the rest folders from internet.
wget "http://apache.techartifact.com/mirror/cxf/2.6.2/apache-cxf-2.6.2.tar.gz"
tar -zxvf './apache-cxf-2.6.2.tar.gz'


pwd=`pwd`
classpath=".:$HOME/libvirt-java/target/libvirt-0.4.8.jar:/usr/share/java/jna.jar"
jarpaths=`ls apache-cxf-2.6.2/lib/*.jar 2> /dev/null`

#loop to implement the classpath
for jarpath in $jarpaths
do
	classpath="$classpath"":"$PWD""/"$jarpath"
done

jsonpath=""

for jarpath in `ls ../lib/*.jar`
        do
		jsonpath="$jsonpath"":"$pwd"/$jarpath"
        done

echo "CLASSPATH=$classpath --- $jsonpath"

classpath=$classpath$jsonpath
echo "Final Classpath: " "$classpath"
java Server $ip $1  $2  $3 $port
