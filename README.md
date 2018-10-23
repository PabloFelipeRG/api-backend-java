## Fidelidade API ##

API feita para estudos de Java.

### Dependências de Software ###

Java 10
- Instalar JDK 10 (sem JRE)  
- Setar variável de ambiente JAVA_HOME apontando para diretório de instalação do java  
- Colocar %JAVA_HOME%\bin no PATH  

Apache Maven  
- Instalar Apache Maven 3.5.3  
- Setar variável de ambiente MAVEN_HOME apontando para diretório de instalação do maven  
- Colocar %MAVEN_HOME%\bin no PATH

Vagrant + VirtualBox
- Por linha de comando, navegar até o diretório "vm" dentro do diretório do projeto e executar
  o comando **vagrant up**.
- Após as instalações e provisionamento da VM tiverem sido concluídos, execute o comando **vagrant ssh**
  para entrar no ambiente.
- Execute o comando **sudo mysql -u dev -p123** para acessar o banco de dados já provisionado e com a estrutura
  de tabelas pronta.
- Para sair do ambiente execute o comando **exit** e para desligar a VM execute o comando **vagrant halt**.


### Construção e Implantação ##


Em homologação ou produção, rodar **mvn clean package** para gerar o pacote.  
Para configurar o Jetty, copie o conteúdo da pasta /config/jetty para o diretório base do Jetty (jetty.base).  


### Documentação ###

Para ver a documentação da api, acesse:  

http://localhost:9000/fidelidade/v1/swagger.json  
http://localhost:9000/fidelidade/v1/swagger.yaml  


### Desenvolvimento - Debugging ###

Para habilitar debug remoto, basta passar os seguintes parâmetros na linha de comando do Maven:  

-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n  
