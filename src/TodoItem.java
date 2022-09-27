public class TodoItem {
    public String TaskTitle;
    public String TaskDescription;
    public boolean TaskIsCompleted;

    public TodoItem(String taskTitle, String taskDescription, boolean taskIsCompleted) {
        TaskTitle = taskTitle;
        TaskDescription = taskDescription;
        TaskIsCompleted = taskIsCompleted;
    }

    public String getTaskTitle() { return TaskTitle; }

    public String getTaskDescription() { return TaskDescription; }

    public boolean getTaskIsCompleted() { return TaskIsCompleted; }

    @Override
    public String toString() {
        return "\n" + "TITLE: " + TaskTitle + "\nDESCRIPTION: " + TaskDescription + "\nCOMPLETED: " + TaskIsCompleted + "\n";
    }
}
