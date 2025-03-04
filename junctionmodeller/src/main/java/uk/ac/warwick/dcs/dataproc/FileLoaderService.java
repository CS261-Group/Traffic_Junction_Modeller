package uk.ac.warwick.dcs.dataproc;
//b) Make a FileLoaderService that is similar to FormLoaderService and hold a Singleton instance of it in DataServiceBuilder (example with FormLoaderService) 

import java.util.List;

import org.javatuples.Pair;

import uk.ac.warwick.dcs.contracts.JunctionConfiguration;
import uk.ac.warwick.dcs.dataproc.loading.FileLoader;

public class FileLoaderService implements ILoaderService<String>{
    
    @Override
    public Pair<JunctionConfiguration, List<String>> load(String filePath){
        FileLoader fileLoader = new FileLoader(filePath);
        JunctionConfiguration junctionConfig = fileLoader.load();
        String loadErrors = fileLoader.getLoadErrors();
        if (fileLoader.getLoadErrors() == null) {
            return new Pair<>(junctionConfig, null);
        } else {
            return new Pair<>(null, List.of(loadErrors));
        }
    }
}
