package com.example.tutora.data;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TutorSeeder {

    public static void seedTutors() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        addTutor(
                db,
                "john_cruz",
                "John Cruz",
                "Math tutor who helps students understand lessons step by step.",
                Arrays.asList("Mathematics"),
                Arrays.asList("Grade 7", "Grade 8", "Grade 9", "Grade 10"),
                Arrays.asList(
                        "Grade 7|Mathematics",
                        "Grade 8|Mathematics",
                        "Grade 9|Mathematics",
                        "Grade 10|Mathematics"
                ),
                "6 years teaching Mathematics",
                "Algebra, Geometry, Problem-solving",
                "Step-by-step explanation, easy-to-understand methods",
                "₱180/hour",
                4.8
        );

        addTutor(
                db,
                "maria_santos",
                "Maria Santos",
                "English and Filipino tutor for junior high school students.",
                Arrays.asList("English", "Filipino"),
                Arrays.asList("Grade 7", "Grade 8", "Grade 9", "Grade 10"),
                Arrays.asList(
                        "Grade 7|English", "Grade 8|English", "Grade 9|English", "Grade 10|English",
                        "Grade 7|Filipino", "Grade 8|Filipino", "Grade 9|Filipino", "Grade 10|Filipino"
                ),
                "5 years teaching in junior high school",
                "Grammar, reading comprehension, essay writing",
                "Friendly and interactive, focuses on improving communication skills",
                "₱150/hour",
                4.7
        );

        addTutor(
                db,
                "mark_dela_cruz",
                "Mark Dela Cruz",
                "Tutor for TLE, MAPEH, and practical learning subjects.",
                Arrays.asList("Cookery", "CSS", "Music", "Arts", "Physical Education", "Health"),
                Arrays.asList("Grade 7", "Grade 8", "Grade 9", "Grade 10"),
                Arrays.asList(
                        "Grade 7|Cookery", "Grade 8|Cookery", "Grade 9|Cookery", "Grade 10|Cookery",
                        "Grade 7|CSS", "Grade 8|CSS", "Grade 9|CSS", "Grade 10|CSS",
                        "Grade 7|Music", "Grade 8|Music", "Grade 9|Music", "Grade 10|Music",
                        "Grade 7|Arts", "Grade 8|Arts", "Grade 9|Arts", "Grade 10|Arts",
                        "Grade 7|Physical Education", "Grade 8|Physical Education", "Grade 9|Physical Education", "Grade 10|Physical Education",
                        "Grade 7|Health", "Grade 8|Health", "Grade 9|Health", "Grade 10|Health"
                ),
                "5 years teaching practical subjects",
                "ICT basics, arts, music, and physical education",
                "Hands-on and activity-based learning",
                "₱140/hour",
                4.6
        );

        addTutor(
                db,
                "angela_reyes",
                "Angela Reyes",
                "Science tutor who uses examples and simple experiments.",
                Arrays.asList("Science"),
                Arrays.asList("Grade 7", "Grade 8", "Grade 9", "Grade 10"),
                Arrays.asList(
                        "Grade 7|Science",
                        "Grade 8|Science",
                        "Grade 9|Science",
                        "Grade 10|Science"
                ),
                "4 years teaching Science",
                "Biology, Chemistry, Physics basics",
                "Uses examples and simple experiments for better understanding",
                "₱170/hour",
                4.9
        );

        addTutor(
                db,
                "lian_fernandez",
                "Lian Fernandez",
                "Tutor for social studies and values education.",
                Arrays.asList("Araling Panlipunan", "Values/ESP"),
                Arrays.asList("Grade 7", "Grade 8", "Grade 9", "Grade 10"),
                Arrays.asList(
                        "Grade 7|Araling Panlipunan", "Grade 8|Araling Panlipunan", "Grade 9|Araling Panlipunan", "Grade 10|Araling Panlipunan",
                        "Grade 7|Values/ESP", "Grade 8|Values/ESP", "Grade 9|Values/ESP", "Grade 10|Values/ESP"
                ),
                "7 years teaching social and values education",
                "History, culture, ethics, and values formation",
                "Discussion-based, encourages critical thinking",
                "₱160/hour",
                4.8
        );
    }

    private static void addTutor(FirebaseFirestore db, String id, String name, String bio,
                                 Object subjects, Object gradeLevels, Object teachingKeys,
                                 String experience, String specialization, String teachingStyle,
                                 String feeDetails, double rating) {
        Map<String, Object> tutor = new HashMap<>();
        tutor.put("name", name);
        tutor.put("bio", bio);
        tutor.put("subjects", subjects);
        tutor.put("gradeLevels", gradeLevels);
        tutor.put("teachingKeys", teachingKeys);
        tutor.put("experience", experience);
        tutor.put("specialization", specialization);
        tutor.put("teachingStyle", teachingStyle);
        tutor.put("feeDetails", feeDetails);
        tutor.put("rating", rating);

        db.collection("tutors").document(id).set(tutor);
    }
}
