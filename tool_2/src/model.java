import java.util.ArrayList;
import java.util.List;

public class model {
    public String actor = new String();
    public List<String> verb = new ArrayList<>(); //读取并获得
    public List<String> PI = new ArrayList<>();
    public List<String> purpose = new ArrayList<>();

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public List<String> getVerb() {
        return verb;
    }

    public void setVerb(List<String> verb) {
        this.verb = verb;
    }

    public void addVerb(String verb) {
        this.verb.add(verb);
    }

    public List<String> getPI() {
        return PI;
    }

    public void setPI(List<String> PI) {
        this.PI = PI;
    }

    public void addPI(String pi) {
        this.PI.add(pi);
    }

    public List<String> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<String> purpose) {
        this.purpose = purpose;
    }

    public void addPurpose(String purpose){
        this.purpose.add(purpose);
    }

    public model getModel(String str) {
        for (int i = 0; i < str.length(); ) {
            char ch = str.charAt(i);
            switch (ch) {

                case '(':
                    for (int j = i + 1; j < str.length(); j++) {
                        if (str.charAt(j) == ')') {
                            String tmp = str.substring(i + 1, j);
                            this.setActor(tmp);
                            i = j + 1;
                            break;
                        }
                    }
                    break;

                case '[':
                    for (int j = i + 1; j < str.length(); j++) {
                        if (str.charAt(j) == ']') {
                            String tmp = str.substring(i + 1, j);
                            this.addVerb(tmp);
                            i = j + 1;
                            break;
                        }
                    }
                    break;

                case '<':
                    for (int j = i + 1; j < str.length(); j++) {
                        if (str.charAt(j) == '>') {
                            String tmp = str.substring(i + 1, j);
                            this.addPI(tmp);
                            i = j + 1;
                            break;
                        }
                    }
                    break;

                case '{':
                    for (int j = i + 1; j < str.length(); j++) {
                        if (str.charAt(j) == '}') {
                            String tmp = str.substring(i + 1, j);
                            this.addPurpose(tmp);
                            i = j + 1;
                            break;
                        }
                    }
                    break;

                default:
                    i++;
                    break;
            }
        }
        //如果语句中没有出现收集的主语，那么认为是第一方收集
        if(this.actor.equals(""))
            this.setActor("我们");
        return this;
    }
}
