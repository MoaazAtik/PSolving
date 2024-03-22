package com.example.jconsoleapp.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

// #L.841
public class KeysAndRooms {

    public static void preCanVisitAllRooms() {
        // Graph, 2D Array
//        Integer[][] roomsArray = {{1}, {2}, {3}, {}};
//        Integer[][] roomsArray = {{1, 3}, {3, 0, 1}, {2}, {0}};
//        Integer[][] roomsArray = {{2}, {}, {1}};
        Integer[][] roomsArray = {{4}, {3}, {}, {2, 5, 7}, {1}, {}, {8, 9}, {}, {}, {6}};
        //                         0    1    2      3       4    5    6     7    8   9
        List<List<Integer>> rooms = array2DToList2D(roomsArray);

        p(rooms);
//        p(canVisitAllRooms(rooms));
//        p(canVisitAllRooms2(rooms));
//        p(canVisitAllRooms3(rooms));
        p(canVisitAllRooms4(rooms));
        p(canVisitAllRooms5(rooms));
        p(canVisitAllRooms6(rooms));
    }

    // Could be true if the room should be visited in order Only.
    private static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> keys = new HashSet<>();
        keys.add(0);

        for (int i = 0; i < rooms.size(); i++) {
            if (!keys.contains(i))
                return false;
            List<Integer> currentRoomKeys = rooms.get(i);
            keys.addAll(currentRoomKeys);
            // OR
//            for (Integer key : currentRoomKeys) {
//                keys.add(key);
//            }
        }
        p(keys);

        return true;
    }

    // wrong
    private static boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        Set<Integer> keys = new HashSet<>();
        keys.add(0);

//        rooms.forEach(keys::addAll);
        // OR
        for (List<Integer> currentRoomKeys : rooms)
            keys.addAll(currentRoomKeys);
        for (int i = 0; i < rooms.size(); i++) {
            if (!keys.contains(i))
                return false;
        }

        p(keys);
        return true;
    }

    // wrong
    private static boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        // {{4}, {3}, {}, {2, 5, 7}, {1}, {}, {8, 9}, {}, {}, {6}} wrong
        Set<Integer> keys = new HashSet<>();
        keys.add(0);

        for (int roomNo = 0; roomNo < rooms.size(); roomNo++) {
            List<Integer> currentRoom = rooms.get(roomNo);
            if (currentRoom.isEmpty())
                continue;
            for (int keyOrder = 0; keyOrder < currentRoom.size(); keyOrder++) {
                if (currentRoom.get(keyOrder) != roomNo)
                    keys.add(currentRoom.get(keyOrder));
            }
        }
        for (int i = 0; i < rooms.size(); i++) {
            if (!keys.contains(i))
                return false;
        }

        p(keys);

        return true;
    }

    // Iterative 1ms - 44.5mb
    private static boolean canVisitAllRooms4(List<List<Integer>> rooms) {
        boolean[] roomsVisited = new boolean[rooms.size()];
        Queue<Integer> roomsQueue = new LinkedList<>();

        roomsQueue.offer(0);
        while (!roomsQueue.isEmpty()) {
            int room = roomsQueue.poll();
            pl(room);
            if (roomsVisited[room])
                continue;
            List<Integer> roomKeys = rooms.get(room);
            roomsQueue.addAll(roomKeys);
            roomsVisited[room] = true;
        }
        p();
        for (boolean visited : roomsVisited)
            if (!visited) return false;
        return true;
    }

    // Iterative - YT modified 1ms - 44.3mb
    private static boolean canVisitAllRooms5(List<List<Integer>> rooms) {
        boolean[] roomsVisited = new boolean[rooms.size()];
        Queue<Integer> roomsQueue = new LinkedList<>();

        roomsQueue.offer(0);
        roomsVisited[0] = true;

        while (!roomsQueue.isEmpty()) {
            int room = roomsQueue.poll();
            pl(room);
            for (Integer newKey : rooms.get(room)) {
                if (!roomsVisited[newKey]) {
                    roomsQueue.add(newKey);
                    roomsVisited[newKey] = true;
                }
            }
        }
        p();
        for (boolean visited : roomsVisited)
            if (!visited) return false;
        return true;
    }

    // Semi Recursive - L 0ms - 43.8mb
    private static boolean canVisitAllRooms6(List<List<Integer>> rooms) {
        boolean[] vis = new boolean[rooms.size()];
//        dfs(rooms, vis, 0);
        dfs2(rooms, vis, 0);
        for (boolean i : vis) if (!i) return false;
        return true;
    }

    private static void dfs(List<List<Integer>> rooms, boolean[] vis, int cur) {
        pl(cur);
        if (vis[cur]) return;
        vis[cur] = true;
        for (int i : rooms.get(cur)) dfs(rooms, vis, i);
    }

    // without return base case
    private static void dfs2(List<List<Integer>> rooms, boolean[] vis, int cur) {
        pl(cur);
//        if (vis[cur]) return;
        vis[cur] = true;
        for (int i : rooms.get(cur))
            if (!vis[i])
                dfs2(rooms, vis, i);
    }


    private static List<List<Integer>> array2DToList2D(Integer[][] roomsArray) {
        List<List<Integer>> roomsList = new ArrayList<>();
        for (Integer[] keysArray : roomsArray) {
            roomsList.add(Arrays.stream(keysArray).collect(Collectors.toList()));
            // OR
//            List<Integer> keysList = Arrays.stream(keysArray).collect(Collectors.toList());
//            roomsList.add(keysList);
            // OR
//            List<Integer> keysList = new ArrayList<>();
//            Collections.addAll(keysList, keysArray);
//            roomsList.add(keysList);
        }
        return roomsList;

        // OR
//        List<List<Integer>> roomsList = new ArrayList<>();
//        for (int i = 0; i < roomsArray.length; i++) {
//            List<Integer> keysList = new ArrayList<>();
//            for (int j = 0; j < roomsArray[i].length; j++) {
//                Integer key = roomsArray[i][j];
//                keysList.add(key);
//            }
//            roomsList.add(keysList);
//        }
//        return roomsList;
    }

}
