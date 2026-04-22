## Ажиллуулах комманд
--> java -cp target/bd-1.0-SNAPSHOT.jar com.biyDaalt.Main Cards.txt (шууд тоглоом эхлэнэ)
### --help command харах үед 
--> java -cp target/bd-1.0-SNAPSHOT.jar com.biyDaalt.Main --help
    
    **** FLASHCARD ****
    flashcard <cards-file> [options]
    --help                Тусламж харуулах
    --order <order>       Дараалал (random, worst-first, recent-mistakes-first)
    --repetitions <num>   Зөв хариулах оролдлогын тоо
    --invertCards         Асуулт, хариултыг солих
    
### --order, --repetitions, --invertCards тохируулах үед
--> java -cp target/bd-1.0-SNAPSHOT.jar com.biyDaalt.Main Cards.txt --order (order)

--> java -cp target/bd-1.0-SNAPSHOT.jar com.biyDaalt.Main Cards.txt --repetitions (num)

--> java -cp target/bd-1.0-SNAPSHOT.jar com.biyDaalt.Main Cards.txt --invertCards
