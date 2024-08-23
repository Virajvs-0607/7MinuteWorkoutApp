package vvs.tutorial.a7minuteworkoutapp

object Constants {
    fun defaultExerciseList(): ArrayList<exerciseModel>{
        val exerciseList = ArrayList<exerciseModel>()
        val jumpingJacks = exerciseModel(1,"Jumping Jacks",R.drawable.ic_jumping_jacks,false,false)
        exerciseList.add(jumpingJacks)

        val wallSit = exerciseModel(2,"Wall Sit",R.drawable.ic_wall_sit,false,false)
        exerciseList.add(wallSit)

        val pushUp = exerciseModel(3,"Push Up",R.drawable.ic_push_up,false,false)
        exerciseList.add(pushUp)

        val stepUpOnChair = exerciseModel(4,"Step-Up on Chair",R.drawable.ic_step_up_onto_chair,false,false)
        exerciseList.add(stepUpOnChair)

        val squat = exerciseModel(5,"Squat",R.drawable.ic_squat,false,false)
        exerciseList.add(squat)

        val tricepDipOnChair = exerciseModel(6,"Tricep Dip on Chair",R.drawable.ic_triceps_dip_on_chair,false,false)
        exerciseList.add(tricepDipOnChair)

        val plank = exerciseModel(7,"Plank",R.drawable.ic_plank,false,false)
        exerciseList.add(plank)

        val lunges = exerciseModel(8,"Lunges",R.drawable.ic_lunge,false,false)
        exerciseList.add(lunges)

        val pushUpAndRotation = exerciseModel(9,"Push Up and Rotation",R.drawable.ic_push_up_and_rotation,false,false)
        exerciseList.add(pushUpAndRotation)

        val sidePlank = exerciseModel(10,"Side Plank",R.drawable.ic_side_plank,false,false)
        exerciseList.add(sidePlank)


        return exerciseList
    }
}