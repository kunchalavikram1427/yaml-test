/*** BEGIN META {
 "name" : "Read SMR Git Repo Contents",
 "comment" : "Read SMR Git Repo Contents",
 "parameters" : [ 'GIT_URL' ],
 "core": "2.263.1.2",
 "authors" : [
 { name : "Vikram Babu Kunchala" }
 ]
 } END META**/
import groovy.json.JsonSlurper
import java.util.regex.Pattern
import hudson.*;
import hudson.model.*;
import jenkins.*;
import jenkins.model.*;
import groovy.json.*
import org.yaml.snakeyaml.Yaml;
def yamlFiles = []
def gitOrgName = 'kunchalavikram1427' 
def gitRepoName = 'sample_terraform_code'
def fileList = ["curl", "-H", "Authorization: token xxx", "-H", "Accept: application/vnd.github.v3.raw", "-L", "https://api.github.com/repos/${gitOrgName}/${gitRepoName}/contents/"]
yamlFiles.addAll(new JsonSlurper().parseText(fileList.execute().text).findAll { it.name.endsWith('.yml') })

// print yamlFiles
def uniqueSecurityPillars = []
// Iterate over each YAML file
yamlFiles.each { yamlFile ->
    def fileDownURL =["curl", "-H", "Authorization: token xxx", "-H", "Accept: application/vnd.github.v3.raw", "-L", yamlFile.download_url]
    // def yamlContent = new JsonSlurper().parseText(fileDownURL.execute().text)
    def yamlContent = new Yaml().load(fileDownURL.execute().text)
    
    def securityPillar = yamlContent['security-pillar']
    
    if (securityPillar && !uniqueSecurityPillars.contains(securityPillar)) {
        uniqueSecurityPillars.add(securityPillar)
    }
}
print uniqueSecurityPillars
return uniqueSecurityPillars