package edu.uw.ischool.ryancho7.quizdroidbasics.data

import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question

object QuizData {

    // Math Questions
    fun getMathQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What is 2 + 2?",
                options = listOf("3", "4", "5", "6"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What is 5 x 5?",
                options = listOf("10", "20", "25", "30"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the absolute value of -10?",
                options = listOf("10", "-10", "0", "1"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is 5!?",
                options = listOf("125", "155", "120", "150"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is 100 - 91?",
                options = listOf("0", "11", "19", "9"),
                correctAnswer = 3
            ),
            Question(
                questionText = "What is 1 x 0 x 1?",
                options = listOf("0", "1", "10", "-1"),
                correctAnswer = 0
            )
        )
    }

    // Physics Questions
    fun getPhysicsQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What is the unit of force?",
                options = listOf("Joule", "Newton", "Pascal", "Watt"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What is the speed of light?",
                options = listOf("300,000 km/s", "150,000 km/s", "400,000 km/s", "500,000 km/s"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is the formula for calculating kinetic energy?",
                options = listOf("KE = mv^2", "KE = 1/2 mv^2", "KE = mgh", "KE = Fd"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What is the acceleration due to gravity on Earth?",
                options = listOf("9.8 m/s²", "10 m/s²", "8.5 m/s²", "9.0 m/s²"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Which law states that for every action, there is an equal and opposite reaction?",
                options = listOf("Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Law of Conservation of Energy"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the principle behind a lever?",
                options = listOf("Mechanical Advantage", "Conservation of Momentum", "Work-Energy Principle", "Archimedes' Principle"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Which particle has a negative charge?",
                options = listOf("Proton", "Neutron", "Electron", "Alpha Particle"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the SI unit of electric current?",
                options = listOf("Volt", "Ohm", "Ampere", "Watt"),
                correctAnswer = 2
            ),
            Question(
                questionText = "In which medium does sound travel fastest?",
                options = listOf("Air", "Water", "Vacuum", "Steel"),
                correctAnswer = 3
            )
        )
    }

    // Marvel Super Heroes Questions
    fun getMarvelQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "Who is Iron Man?",
                options = listOf("Bruce Wayne", "Peter Parker", "Tony Stark", "Clark Kent"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is Captain America's shield made of?",
                options = listOf("Adamantium", "Vibranium", "Iron", "Steel"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What is the real name of the superhero Black Panther?",
                options = listOf("T'Challa", "N'Jadaka", "M'Baku", "Shuri"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Which infinity stone is located on Vision's forehead?",
                options = listOf("Power Stone", "Reality Stone", "Mind Stone", "Soul Stone"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the name of Thor’s hammer?",
                options = listOf("Vanir", "Mjolnir", "Aesir", "Norn"),
                correctAnswer = 1
            ),
            Question(
                questionText = "Who is the Winter Soldier?",
                options = listOf("Steve Rogers", "Tony Stark", "Bucky Barnes", "Clint Barton"),
                correctAnswer = 2
            ),
            Question(
                questionText = "Which of the following characters is from Wakanda?",
                options = listOf("Thor", "Star-Lord", "T'Challa", "Loki"),
                correctAnswer = 2
            ),
            Question(
                questionText = "Who is the director of S.H.I.E.L.D.?",
                options = listOf("Nick Fury", "Phil Coulson", "Maria Hill", "Tony Stark"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Who was able to pick up Thor’s hammer in Avengers: Endgame?",
                options = listOf("Hulk", "Tony Stark", "Steve Rogers", "Natasha Romanoff"),
                correctAnswer = 2
            )
        )
    }

    fun getStudioGhibliQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What is the name of the spirit in 'Spirited Away'?",
                options = listOf("No-Face", "Totoro", "Howl", "Ponyo"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Which Studio Ghibli film features a flying castle?",
                options = listOf("My Neighbor Totoro", "Castle in the Sky", "Kiki's Delivery Service", "Princess Mononoke"),
                correctAnswer = 1
            ),
            Question(
                questionText = "Who directed most Studio Ghibli films?",
                options = listOf("Hayao Miyazaki", "Isao Takahata", "Goro Miyazaki", "Makoto Shinkai"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What type of animal is Jiji in 'Kiki's Delivery Service'?",
                options = listOf("Dog", "Cat", "Bird", "Rabbit"),
                correctAnswer = 1
            ),
            Question(
                questionText = "In 'My Neighbor Totoro', what do Satsuki and Mei find in the forest?",
                options = listOf("A dragon", "A giant cat", "Totoro", "A fairy"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the name of the bathhouse in 'Spirited Away'?",
                options = listOf("Aburaya", "Yubaba's Bathhouse", "Chihiro's Bathhouse", "Zeniba's Bathhouse"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Which film features a princess fighting against industrialization?",
                options = listOf("Princess Mononoke", "Spirited Away", "Nausicaä of the Valley of the Wind", "Howl's Moving Castle"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is the main theme of 'Ponyo'?",
                options = listOf("Friendship", "Love", "Nature", "Family"),
                correctAnswer = 2
            )
        )
    }

    fun getMarioQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What is Mario's profession?",
                options = listOf("Doctor", "Plumber", "Carpenter", "Firefighter"),
                correctAnswer = 1
            ),
            Question(
                questionText = "Who is Mario's brother?",
                options = listOf("Luigi", "Wario", "Peach", "Toad"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is the name of Mario's arch-nemesis?",
                options = listOf("Bowser", "Donkey Kong", "King K. Rool", "Waluigi"),
                correctAnswer = 0
            ),
            Question(
                questionText = "In which game did Mario first appear?",
                options = listOf("Super Mario Bros.", "Donkey Kong", "Mario Kart", "Super Mario 64"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What does Mario collect to gain extra lives?",
                options = listOf("Stars", "Mushrooms", "Coins", "Power-ups"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What is the name of Mario's dinosaur companion?",
                options = listOf("Yoshi", "Toad", "Peach", "Bowser"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What item allows Mario to fly?",
                options = listOf("Super Star", "Cape Feather", "Fire Flower", "Tanooki Suit"),
                correctAnswer = 1
            ),
            Question(
                questionText = "What is Princess Peach often kidnapped by?",
                options = listOf("Bowser", "Wario", "Donkey Kong", "Lakitu"),
                correctAnswer = 0
            )
        )
    }

    fun getHarryPotterQuestions(): List<Question> {
        return listOf(
            Question(
                questionText = "What house is Harry Potter sorted into?",
                options = listOf("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is the name of Harry's owl?",
                options = listOf("Hedwig", "Crookshanks", "Fawkes", "Errol"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Who is the headmaster of Hogwarts during Harry's first year?",
                options = listOf("Albus Dumbledore", "Severus Snape", "Minerva McGonagall", "Rubeus Hagrid"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What spell is used to disarm an opponent?",
                options = listOf("Expelliarmus", "Stupefy", "Lumos", "Avada Kedavra"),
                correctAnswer = 0
            ),
            Question(
                questionText = "What is the name of the school Harry attends?",
                options = listOf("Hogwarts", "Beauxbatons", "Durmstrang", "Ilvermorny"),
                correctAnswer = 0
            ),
            Question(
                questionText = "Who are Harry's two best friends?",
                options = listOf("Ron and Draco", "Hermione and Neville", "Ron and Hermione", "Draco and Pansy"),
                correctAnswer = 2
            ),
            Question(
                questionText = "What creature guards the entrance to Gryffindor Tower?",
                options = listOf("A troll", "A hippogriff", "A sphinx", "The Fat Lady"),
                correctAnswer = 3
            ),
            Question(
                questionText = "What is the name of the dark wizard who is the main antagonist?",
                options = listOf("Tom Riddle", "Lucius Malfoy", "Bellatrix Lestrange", "Voldemort"),
                correctAnswer = 3
            )
        )
    }
}
