package uk.ac.nott.cs.comp2013.mentorapp.view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Manages the scenes available in the application and controls which scene is current presented in
 * the stage.
 */
public class ViewManager {

    public static final String LOGIN = "login_view";
    public static final String DUMMY = "dummy_view";
    public static final String MENTOR = "mentor_view";
    public static final String MENTEE = "mentee_view";
    public static final String ADMIN = "admin_view";

    private final Stage stage;
    private final Map<String, Parent> scenes = new HashMap<>();

    /**
     * Construct a new manager.
     *
     * @param stage the stage used to display this manager's scenes
     */
    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Add a new view to the manager.
     *
     * @param key unique key to identify the scene
     * @param s   view
     */
    public <T extends Parent & ManagedView> void addView(String key, T s) {
        scenes.put(key, s);
        s.setOnViewChange(e -> setStageView(e.getView()));
    }

    /**
     * Set which view should be displayed on the stage. Will do nothing if the given key does not
     * exist.
     *
     * @param key key of scene to display
     */
    public void setStageView(String key) {
        checkViewAvailable(key);

        // clear old state data
        Scene old = stage.getScene();

        if (old != null) {
            old.setRoot(new StackPane());
        }

        Parent parent = scenes.get(key);

        // update view data
        if (parent instanceof ReloadableView) {
            ((ReloadableView) parent).reloadData();
        }

        Scene s = new Scene(scenes.get(key), 500, 400);


        stage.setScene(s);
    }


    public void removeView(String key) {
        checkViewAvailable(key);

        Scene old = stage.getScene();

        if (old != null) {
            old.setRoot(new StackPane());
        }
    }


    private void checkViewAvailable(String key) {
        if (!scenes.containsKey(key)) {
            return;
        }

    }

}
