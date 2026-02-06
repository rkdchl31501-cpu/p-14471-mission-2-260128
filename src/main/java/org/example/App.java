package org.example;

import org.example.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            String action = rq.getAction();

            if (action.equals("종료")) {
                System.out.println("종료");
                break;
            } else if (action.equals("등록")) {
                actionWrite();
            } else if (action.equals("목록")) {
                actionList();
            } else if (action.startsWith("삭제")) {
                actionDelete(rq);
            } else if (action.startsWith("수정")) {
                actionModify(rq);
            }
        }
    }

    // 목록
    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        wiseSayings.forEach(ws ->
                System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getText())
        );
    }

    // 등록 (등록은 파라미터 안 쓰니까 기존 방식 유지)
    private void actionWrite() {
        System.out.print("명언: ");
        String text = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();

        lastId++;
        WiseSaying ws = new WiseSaying(lastId, text, author);
        wiseSayings.add(ws);

        System.out.println(ws.getId() + "번 명언이 등록되었습니다.");
    }

    // 삭제
    private void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }

        boolean deleted = deleteById(id);

        if (deleted) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
        }
    }

    private boolean deleteById(int id) {
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                wiseSayings.remove(i);
                return true;
            }
        }
        return false;
    }

    // 수정
    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }

        WiseSaying ws = findById(id);

        if (ws == null) {
            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
            return;
        }

        System.out.print("새 명언: ");
        String newText = sc.nextLine();

        System.out.print("새 작가: ");
        String newAuthor = sc.nextLine();

        ws.setText(newText);
        ws.setAuthor(newAuthor);

        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    private WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if (ws.getId() == id) {
                return ws;
            }
        }
        return null;
    }


}


