package com.example.Epic.Energy.Services;

import com.example.Epic.Energy.Services.entities.Municipality;
import com.example.Epic.Energy.Services.repositories.MunicipalityRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class Runner implements CommandLineRunner {
    private final Logger logger= LoggerFactory.getLogger("ReggioNellaFottutaEmilia");
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Override
    public void run(String... args) throws Exception {
        Optional<Municipality> optionalMunicipality=municipalityRepository.findById((long)1);
        if(optionalMunicipality.isEmpty()) {
            String municipalities = "csvFiles/comuni-italiani.csv";
            String provinces = "csvFiles/province-italiane.csv";
            String[] line;
            String[] municipalitiesLine;
            try {
                CSVReader municipalitiesReader = new CSVReader(new FileReader(municipalities));
                while ((municipalitiesLine = municipalitiesReader.readNext()) != null) {
                    String[] municipalitySplit = municipalitiesLine[0].split(";");
                    CSVReader provincesReader = new CSVReader(new FileReader(provinces));
                    while ((line = provincesReader.readNext()) != null) {
                        String[] provinceSplit = line[0].split(";");
                        if (municipalitySplit[3].equals(provinceSplit[1])) {
                            Municipality newMunicipality = new Municipality();
                            newMunicipality.setName(municipalitySplit[2]);
                            newMunicipality.setProvince(provinceSplit[1]);
                            newMunicipality.setProvinceAbbr(provinceSplit[0]);
                            newMunicipality.setRegion(provinceSplit[2]);
                            municipalityRepository.save(newMunicipality);
                            break;
                        }
                    }
                }
                logger.info("All municipalities are uploaded");
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
    }
}
