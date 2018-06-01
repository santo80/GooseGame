node{
    def mvnHome = tool "Maven"
    stage('Clone'){
        checkout scm

    }
    stage('Unit Test'){
        sh "mvn clean test"
        sh "ls -l target"
        junit 'target/surefire-reports/**/*.xml'
        def miaVar= "ecco la mia var"
        echo 'visualizzo $miaVar'
        echo "visualizzo  $miaVar"
    }
    stage('Integration Test'){
       echo "qui dovrei lanciare i test di Integrazione"
    }
    stage('Results'){

    }
}