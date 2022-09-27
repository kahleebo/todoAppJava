import java.util.*;

public class TodoApp {
    public List<TodoItem> TodoList;

    public TodoApp() {
        TodoList = new ArrayList<>();
    }

    public List<TodoItem> getTodoList() {
        return TodoList;
    }

    public void addItem(TodoItem todo) {
        TodoList.add(todo);
    }

    public void printTodoList() {
        for (TodoItem todo:TodoList) {
            System.out.println(todo);
        }
    }
}
