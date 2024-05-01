package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("todo")
@RestController
public class ToDoController {

    @GetMapping()
    public String getToDo(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>My To-Do List</title>\n" +
                "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\">\n" +
                "<style>\n" +
                "    body {\n" +
                "        font-family: -apple-system,BlinkMacSystemFont,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial,sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\",\"Segoe UI Symbol\";\n" +
                "        line-height: 1.5;\n" +
                "        color: #333;\n" +
                "        background-color: #f8f8f8;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    .container {\n" +
                "        max-width: 800px;\n" +
                "        margin: 20px auto;\n" +
                "    }\n" +
                "\n" +
                "    .task {\n" +
                "        background-color: #fff;\n" +
                "        margin-bottom: 16px;\n" +
                "        border-radius: 6px;\n" +
                "        box-shadow: 0 0 0 1px rgba(16,22,26,.1), 0 1px 1px rgba(16,22,26,.2), 0 2px 6px rgba(16,22,26,.2);\n" +
                "    }\n" +
                "\n" +
                "    .task-header {\n" +
                "        display: flex;\n" +
                "        align-items: center;\n" +
                "        padding: 10px;\n" +
                "        border-bottom: 1px solid #eaeaea;\n" +
                "    }\n" +
                "\n" +
                "    .task-body {\n" +
                "        padding: 10px;\n" +
                "    }\n" +
                "\n" +
                "    .task input[type=\"checkbox\"] {\n" +
                "        margin-right: 10px;\n" +
                "    }\n" +
                "\n" +
                "    .task input[type=\"text\"] {\n" +
                "        border: none;\n" +
                "        outline: none;\n" +
                "        font-size: 16px;\n" +
                "        width: calc(100% - 30px);\n" +
                "    }\n" +
                "\n" +
                "    .subtask {\n" +
                "        margin-left: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .add-subtask-btn {\n" +
                "        color: #a2a2a2;\n" +
                "        cursor: pointer;\n" +
                "        margin-left: auto;\n" +
                "    }\n" +
                "\n" +
                "    .delete-btn {\n" +
                "        color: #a2a2a2;\n" +
                "        cursor: pointer;\n" +
                "        margin-left: 10px;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"task\">\n" +
                "        <div class=\"task-header\">\n" +
                "            <input type=\"checkbox\">\n" +
                "            <input type=\"text\" placeholder=\"Название задачи\">\n" +
                "            <i class=\"fas fa-plus add-subtask-btn\"></i>\n" +
                "            <i class=\"fas fa-trash delete-btn\"></i>\n" +
                "        </div>\n" +
                "        <div class=\"task-body\">\n" +
                "            <input type=\"text\" class=\"subtask\" placeholder=\"Добавить подзадачу\">\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }
}
