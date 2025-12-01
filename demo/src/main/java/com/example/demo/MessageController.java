package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    // curl -X GET http://localhost:8080/api/messages
    /*@GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }*/
    // curl -X POST http://localhost:8080/api/messages -H "Content-Type: text/plain" -d "Hello World"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    // curl -X GET http://localhost:8080/api/messages/0
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    // curl -X DELETE http://localhost:8080/api/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    // curl -X PUT http://localhost:8080/api/messages/0 -H "Content-Type: text/plain" -d "Updated message"
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    // curl -X GET http://localhost:8080/api/messages/search/text
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> aboba(@PathVariable("text") String
                                                     text) {
        for(int i = 0; i < messages.size(); i++){
            if (messages.get( i ).contains( text )){
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.ok(0);
    }
    // curl -X GET http://localhost:8080/api/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> space() {
        return ResponseEntity.ok(messages.size());
    }
    // curl -X POST http://localhost:8080/api/messages/1480/create
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> guest(@PathVariable("index") Integer index) {
        messages.add(index, index.toString());
        return ResponseEntity.accepted().build();
    }
    // curl -X DELETE http://localhost:8080/api/messages/search/text
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> world(@PathVariable("text") String
                                                 text) {
        for(int i = 0; i < messages.size(); i++){
            if (messages.get( i ).contains( text )){
                messages.remove(i);
                i -= 1;
            }
        }
        return ResponseEntity.noContent().build();
    }
    // curl -X GET http://localhost:8080/api/messages -H "Content-type: application/json" -d "Text2"
    @GetMapping("messages")
    public ResponseEntity<List<String>> water(@RequestBody String
                                                          text) {
        LinkedList<String> otvet = new LinkedList<>();
        for ( int i = 0; i < messages.size(); i++ ){
            if ( messages.get(i).startsWith(text) ){
                otvet.add(messages.get(i));
            }
        }
        return ResponseEntity.ok(otvet);
    }
}

