package com.example.spring.controller;

import com.example.spring.dto.PageRequestDTO;
import com.example.spring.dto.TodoDTO;
import com.example.spring.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // /todo/list 접근시....
    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     Model model) {
        log.info(pageRequestDTO);
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGet() {

        log.info("GET todo register........ ");
    }

    // 4. 객체 자료 수집
    @PostMapping(value = "/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("Post todo register...........");

        if(bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";  //등록후 목록으로 이동...
    }

    // 한개의 Todo 조회하기
    @GetMapping({"/read", "/modify"}) // /read로 들어오면 read.jsp로, /modify로 들어오면 modify.jsp로 이동
    public void read(Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);
    }

    // Todo 삭제하기
    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        log.info("------------------------ remove --------------------------");
        log.info("tno : "+ tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has error................");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());  // GET 파라미터
            return "redirect:/todo/modify";  // /todo/modify?tno=1(todoDTO.getTno()의 값)
        }
        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }


}
