import java.util.ArrayList;
import java.util.Collections;

public class Playlist extends ArrayList<Song>{
	
	public boolean contains(Object o) {
		if (!(o instanceof Song)) {
			return false;
		}
		Song song = (Song) o;
		for (Song s : this) {
			if (s.equals(song)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(Object o) {
		if (!(o instanceof Song)) {
			return false;
		}
		Song song = (Song) o;
		// Find the index of the element to remove
        int index = indexOf(song);

        // If the element is found
        if (index != -1) {
            // Shift subsequent elements to the left
            for (int i = index + 1; i < size(); i++) {
                set(i - 1, get(i));
            }
            // Remove the last element
            remove(size() - 1);
            return true; // Return true indicating the element is removed
        }
        return false; // Return false indicating the element is not found
	}
	
	public void showMissed(Object o) {
		if (!(o instanceof Playlist)) {
			return;
		}
		Playlist obj = (Playlist) o;
		Playlist appleClone = (Playlist) obj.clone();
		Playlist spotifyClone = (Playlist) this.clone();
		for (Song s : this) {
			if (obj.contains(s)){
				appleClone.remove(s);
				spotifyClone.remove(s);
			}
		}
		System.out.println("Spotify extras: \n");
		for (Song s : spotifyClone) {
			System.out.println(s.name);
		}
		System.out.println("\nApple extras: \n");
		for (Song s : appleClone) {
			System.out.println(s.name);
		}
		
	}
}
