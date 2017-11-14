def call () {
  echo "Running performTest.groovy"
  sh '''
    touch /tmp/test123.txt
  '''
}
