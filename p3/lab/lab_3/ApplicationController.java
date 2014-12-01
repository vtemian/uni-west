public class ApplicationControlller{
    public static void main(String[] args) {}
    public void perform(){
        List<Task> tasks = in.getTasks();
        List<Task> scheduledTasks = scheduler.schedule(tasks);
        simulator.simulate(scheduledTasks);
    }
}
