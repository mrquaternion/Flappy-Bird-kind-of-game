public class Controller {
    private Model model;
    private View view;

    public Controller() {
        this.model = new Model();
        this.view = new View(model);
    }

    public View getView() {
        return view;
    }
}
