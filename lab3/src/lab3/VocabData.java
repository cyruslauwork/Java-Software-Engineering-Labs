/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class VocabData {

    String entry;
    String meaning;
    String wordClass;

    public VocabData(String entry, String meaning, String wordClass) {
        this.entry = entry;
        this.meaning = meaning;
        this.wordClass = wordClass;
    }

    public String getEntry() {
        return this.entry;
    }

    public void setEntry(String value) {
        this.entry = value;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String value) {
        this.meaning = value;
    }

    public String getWordClass() {
        return this.wordClass;
    }

    public void setWordClass(String value) {
        this.wordClass = value;
    }
}
