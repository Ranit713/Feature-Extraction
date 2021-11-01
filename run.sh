cd src
javac ./main/FeatureExtraction.java
for filename in ../resources/*.v; do
    java main.FeatureExtraction "$filename" &
done
wait
find . -name "*.class" -type f -delete