package com.example.Epic.Energy.Services;

import com.example.Epic.Energy.Services.entities.Municipality;
import com.example.Epic.Energy.Services.repositories.MunicipalityRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.FileReader;
import java.io.IOException;

//@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Override
    public void run(String... args) throws Exception {

        String municipalities="csvFiles/comuni-italiani.csv";
        String provinces="csvFiles/province-italiane.csv";
        String[] line;
        String[] municipalitiesLine;
        try {
            CSVReader municipalitiesReader=new CSVReader(new FileReader(municipalities));
            while((municipalitiesLine=municipalitiesReader.readNext())!=null){
                String[] municipalitySplit=municipalitiesLine[0].split(";");
                CSVReader provincesReader = new CSVReader(new FileReader(provinces));
                while((line=provincesReader.readNext())!=null){
                    String[] provinceSplit=line[0].split(";");
                    if (municipalitySplit[3].equals(provinceSplit[1])){
                        Municipality newMunicipality=new Municipality() ;
                        newMunicipality.setName(municipalitySplit[2]);
                        newMunicipality.setProvince(provinceSplit[1]);
                        newMunicipality.setProvinceAbbr(provinceSplit[0]);
                        newMunicipality.setRegion(provinceSplit[2]);
                        municipalityRepository.save(newMunicipality);
                        break;
                    }
                }
            }
        }catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
