package com.example.springmvc;

import com.example.springmvc.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Для тестов
 */
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final RecordPackegeRepository recordPackegeRepository;
    private final RecordRepository recordRepository;
    private final String baseUrl = "test";

    @GetMapping
    public String index(Model model) {
        model.addAttribute("baseUrl", baseUrl);
        Iterable<RecordPackageEntity> iterable = recordPackegeRepository.findAll();
        for(RecordPackageEntity rec : iterable){
            System.out.println(rec.getId());
            System.out.println(rec.getName());
        }
        //model.addAttribute("users", iterable);
        return "users_list";
    }

    /**
     * Вывод списка строк записей
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listRec(Model model) {
        /**
         * Add record_package
         */
        RecordPackageEntity recordPackageEntity = new RecordPackageEntity();
        recordPackageEntity.setName("Package2");
        recordPackegeRepository.save(recordPackageEntity);

        //RecordPackageEntity recordPackageEntity = recordPackegeRepository.findById(1L).orElseThrow();
        Optional<RecordPackageEntity> recordPackageEntity1 = recordPackegeRepository.findById(1L);
            //System.out.println(recordPackageEntity.getId());
            //System.out.println(recordPackageEntity.getName());

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setName("new test1");
        recordEntity.setRecordPackageEntity(recordPackageEntity1.get());
        recordRepository.save(recordEntity);

        model.addAttribute("baseUrl", baseUrl);
        Iterable<RecordEntity> iterable = recordRepository.findAll();
        for(RecordEntity rec : iterable){
            System.out.println(rec.getId());
            System.out.println(rec.getName());
            System.out.println(rec.getRecordPackageEntity().getId());
            System.out.println(rec.getRecordPackageEntity().getName());
        }
        //model.addAttribute("users", iterable);
        return "users_list";
    }
}
