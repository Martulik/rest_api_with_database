package rest;

import org.json.*;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class UserInterface {
    String http = "http://localhost:8080/";
    Map<String, Runnable> commands = new HashMap<>();
    RestTemplate restTemplate = new RestTemplate();

    private String token = null;

    public void printAllCommand() {
        System.out.println("ALL COMMANDS");
        for (String key : commands.keySet()) {
            System.out.println(key);
        }
    }

    {
        Runnable printGroups = () -> {
            String url = http + "group/list";
            doGetCommand(url);
        };
        commands.put("print groups", printGroups);

        Runnable findGroup = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter group id or name");
            String group = in.nextLine();
            String url = null;
            try {
                Integer integer = Integer.parseInt(group);
                url = http + "group/list/" + integer;
            } catch (NumberFormatException e) {
                url = http + "group/list/name/" + group;
            }
            doGetCommand(url);
        };
        commands.put("find group", findGroup);

        Runnable addGroup = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter group name");
            String name = in.nextLine();
            JSONObject req = new JSONObject();
            try {
                req.put("name", name);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String url = http + "group/add";
            doPostCommand(url, req, token);
        };
        commands.put("add group", addGroup);

        Runnable printMarks = () -> {
            String url = http + "marks/list";
            doGetCommand(url);
        };
        commands.put("print marks", printMarks);

        Runnable findMarks = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter mark id");
            Integer id = in.nextInt();
            String url = http + "marks/list/" + id;
            doGetCommand(url);
        };
        commands.put("find mark", findMarks);

        Runnable addMark = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter student_id");
            Integer student_id = in.nextInt();
            System.out.println("enter subject_id");
            Integer subject_id = in.nextInt();
            System.out.println("enter teacher_id");
            Integer teacher_id = in.nextInt();
            System.out.println("enter value");
            Integer value = in.nextInt();
            if (value < 2 || value > 5) {
                throw new InputMismatchException("Wrong input format");
            }
            JSONObject req = new JSONObject();
            try {
                req.put("student_id", student_id);
                req.put("subject_id", subject_id);
                req.put("teacher_id", teacher_id);
                req.put("value", value);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String url = http + "marks/add";
            doPostCommand(url, req, token);
        };
        commands.put("add mark", addMark);

        Runnable averageMark = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter student_id");
            Integer id = Integer.valueOf(in.nextLine());
            System.out.println("enter subject name");
            String sub = in.nextLine();
            String url = http + "marks/" + id + '/' + sub;
            doGetCommand(url);
        };
        commands.put("average mark", averageMark);

        Runnable studentMark = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter student_id");
            Integer id = in.nextInt();
            String url = http + "marks/student/" + id;
            doGetCommand(url);
        };
        commands.put("student marks", studentMark);

        Runnable printPeople = () -> {
            String url = http + "people/list";
            doGetCommand(url);
        };
        commands.put("print people", printPeople);

        Runnable findPeople = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter people id");
            Integer id = in.nextInt();
            String url = http + "people/list/" + id;
            doGetCommand(url);
        };
        commands.put("find people", findPeople);

        Runnable addPeople = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter first_name");
            String first_name = in.nextLine();
            System.out.println("enter last_name");
            String last_name = in.nextLine();
            System.out.println("enter pather_name");
            String pather_name = in.nextLine();
            System.out.println("enter group_id (if it's teacher, enter 0)");
            Integer group_id = in.nextInt();
            String type = null;
            if (group_id == 0) {
                group_id = null;
                type = "T";
            } else {
                type = "S";
            }
            JSONObject req = new JSONObject();
            try {
                req.put("first_name", first_name);
                req.put("last_name", last_name);
                req.put("pather_name", pather_name);
                req.put("group_id", group_id);
                req.put("type", type);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String url = http + "people/add";
            doPostCommand(url, req, token);
        };
        commands.put("add people", addPeople);

        Runnable getGroupmates = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter group id or name");
            String group = in.nextLine();
            String url = null;
            try {
                Integer integer = Integer.parseInt(group);
                url = http + "people/list/groupId/" + integer;
            } catch (NumberFormatException e) {
                url = http + "people/list/groupName/" + group;
            }
            doGetCommand(url);
        };
        commands.put("get groupmates", getGroupmates);

        Runnable getTeacherByMark = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter mark id");
            Integer markId = in.nextInt();
            String url = http + "people/mark/" + markId;
            doGetCommand(url);
        };
        commands.put("get teacher by mark", getTeacherByMark);

        Runnable getCountPeopleInGroup = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter group name");
            String group = in.nextLine();
            String url = http + "people/students/count/" + group;
            doGetCommand(url);
        };
        commands.put("get count of people in group", getCountPeopleInGroup);

        Runnable printSubjects = () -> {
            String url = http + "subjects/list";
            doGetCommand(url);
        };
        commands.put("print subjects", printSubjects);

        Runnable findSubject = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter subject id");
            Integer id = in.nextInt();
            String url = http + "subjects/list/" + id;
            doGetCommand(url);
        };
        commands.put("find subject", findSubject);

        Runnable addSubject = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter subject name");
            String subjectName = in.nextLine();
            JSONObject req = new JSONObject();
            try {
                req.put("name", subjectName);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String url = http + "subjects/add";
            doPostCommand(url, req, token);
        };
        commands.put("add subject", addSubject);

        Runnable authentication = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("enter user name");
            String userName = in.nextLine();
            System.out.println("enter password");
            String pwd = in.nextLine();
            String url = http + "auth/singin";
            JSONObject req = new JSONObject();
            try {
                req.put("userName", userName);
                req.put("password", pwd);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(req.toString(), headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
            String token = responseEntity.getBody();
            token = Objects.requireNonNull(token).substring(token.indexOf("\"token\":\""), token.indexOf("\"}"));
            token = token.substring(9);
            this.token = token;
        };
        commands.put("authentication", authentication);

    }

    public void getCommand(String cmd) {
        if (commands.containsKey(cmd)) {
            commands.get(cmd).run();
        } else if (!cmd.equals("exit")) {
            System.out.println("There is no such command");
        }
    }

    public void doPostCommand(String url, JSONObject requestJson, String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token == null) {
            System.out.println("You don't authorized");
            return;
        }
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void doGetCommand(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String result = responseEntity.getBody();
        result = Objects.requireNonNull(result).replaceAll("},", "\n");
        System.out.println(result);
    }
}
