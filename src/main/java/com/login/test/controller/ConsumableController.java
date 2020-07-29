package com.login.test.controller;

import com.login.test.entity.Consumable;
import com.login.test.entity.ItemsForSale;
import com.login.test.repository.ConsumableRepository;
import com.login.test.repository.ItemsForSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ConsumableController {

    @Autowired
    private ItemsForSaleRepository itemsForSaleRepository;

    @Autowired
    private ConsumableRepository consumableRepository;

    @GetMapping("/index")
    public String getConsumables(Model out) {

        out.addAttribute("consumables", consumableRepository.findAll());

        return "consumables";
    }

    @GetMapping("/consumables/register")
    public String getRegister(Model out,
                              @RequestParam Long idConsumable) {

        Optional<Consumable> optionalConsumable = consumableRepository.findById(idConsumable);
        Consumable consumable = new Consumable();
        if (optionalConsumable.isPresent()) {
            consumable = optionalConsumable.get();
        }
        out.addAttribute("consumable", consumable);
        out.addAttribute("allItemsForSales", itemsForSaleRepository.findAll());

        // call the method getItemsForSale in Consumable
        List<ItemsForSale> itemsForSales = new ArrayList<>();
        Method method = getMethod(consumable, "getItemsForSales",
                new Class[]{});
        if (method != null) {
            try {
                itemsForSales = (List<ItemsForSale>) method.invoke(consumable);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        out.addAttribute("consumableItemsForSale", itemsForSales);

        return "register";
    }

    @PostMapping("/consumables/register")
    public String postRegister(@RequestParam Long idConsumable,
                               @RequestParam Long idItemsForSale) {

        Optional<Consumable> optionalConsumable = consumableRepository.findById(idConsumable);
        if (optionalConsumable.isPresent()) {
            Consumable consumable = optionalConsumable.get();

            Optional<ItemsForSale> optionalItemsForSale = itemsForSaleRepository.findById(idItemsForSale);
            if (optionalItemsForSale.isPresent()) {
                ItemsForSale itemsForSale = optionalItemsForSale.get();

                // call the method getItemsForSale in Consumable
                List<ItemsForSale> itemsForSales;
                Method method = getMethod(consumable, "getItemsForSale",
                        new Class[]{});
                if (method != null) {
                    try {
                        itemsForSales = (List<ItemsForSale>) method.invoke(consumable);
                        itemsForSales.add(itemsForSale);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                consumableRepository.save(consumable);
            }
        }

        return "redirect:/consumable/register?idConsumable=" + idConsumable;
    }

    public Method getMethod(Object obj, String methodName, Class[] args) {
        Method method;
        try {
            method = obj.getClass().getDeclaredMethod(methodName, args);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}

