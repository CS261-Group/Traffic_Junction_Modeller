package uk.ac.warwick.dcs.dataproc.validation;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
public class ConfigNameValidator extends Validator<String>{
    
    public ConfigNameValidator(IDiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }
    public List<String> validateConfigNameNotEmpty(String configName){
        List<String> errors = new LinkedList<>();
    
        if(configName == ""){
            errors.add(diagFactory.createEmptyConfigNameMessage());
        }
    
        return errors;
    }
    
    public List<String> validateNoConfigNameClash(String configName){
        List<String> errors = new LinkedList<>();
        
        String path = System.getProperty("user.home")+"/Documents/JunctionModellerConfigurations/"+configName+".json";
        
        File directory = new File(path);
        if (directory.exists()){
            errors.add(diagFactory.createConfigNameClashMessage(configName));
        }
        return errors;
    }

    @Override
    public List<String> validate(String configName){
        
        List<String> errors = validateConfigNameNotEmpty(configName);
        errors.addAll(validateNoConfigNameClash(configName));
        return errors;
    }
}
