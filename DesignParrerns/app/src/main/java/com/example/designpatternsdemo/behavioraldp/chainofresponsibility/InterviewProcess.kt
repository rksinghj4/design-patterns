package com.example.designpatternsdemo.behavioraldp.chainofresponsibility

data class Candidate(
    var qualification: String,//B.Tech, M.Tech, MCA, BE, MBA
    val grade: Float,
    val technology: String,
    val expertiseInDomain: ExpertiseInDomain,//Beginner, Moderate, Expert
    var isQualified: Boolean
)

data class Job(
    val domain: Domain,
    val ctcRange: IntRange = 20000..30000,
    val expertiseNeeded: ExpertiseInDomain
)

interface InterviewHandler {
    fun handleInterview(
        candidate: Candidate,
        job: Job,
    )

    fun after()
}

object RoleRequirements {
    val softwareDevQualification = lazy { listOf("B.Tech", "M.Tech", "MCA", "BE") }.value
    val softwareDevDesignation = lazy { listOf("SE", "SSE", "Tech Lead") }.value

    val managerialRoleQualification =
        lazy { listOf("MBA", "BBA", "CA", "B.Tech", "M.Tech", "MCA", "BE") }.value
    val managerialRoleDesignation = lazy { listOf("HR", "HR manager", "Head of HR") }.value
}

enum class Domain {
    MANAGERIAL, SOFTWARE
}

enum class ExpertiseInDomain {
    BEGINNER, MODERATE, EXPERT
}

class ScreeningRound(
    private val next: InterviewHandler?
) :
    InterviewHandler {
    override fun handleInterview(
        candidate: Candidate,
        job: Job,
    ) {
        when (job.domain) {
            Domain.SOFTWARE -> {
                require(candidate.qualification in RoleRequirements.softwareDevQualification) {
                    println("Candidate doesn't has required qualification")
                }
                require(candidate.grade > 7) {
                    println("Candidate doesn't has required grades")
                }
                require(candidate.expertiseInDomain.ordinal > job.expertiseNeeded.ordinal) {
                    println("Candidate doesn't has required Expertise")
                }
                next?.handleInterview(candidate, job)
            }

            Domain.MANAGERIAL -> {
                require(candidate.qualification in RoleRequirements.managerialRoleQualification) {
                    println("Candidate doesn't has required qualification")
                }
                require(candidate.grade > 6.5) {
                    println("Candidate doesn't has required grades")
                }
                require(candidate.expertiseInDomain.ordinal > job.expertiseNeeded.ordinal) {
                    println("Candidate doesn't has required Expertise")
                }
                next?.handleInterview(candidate, job)
            }
        }

        after()
    }

    override fun after() {
        println("ScreeningRound passed")
    }

}

class TechnicalRound(
    private val next: InterviewHandler?
) :
    InterviewHandler {
    override fun handleInterview(
        candidate: Candidate,
        job: Job,
    ) {
        when (job.domain) {
            Domain.SOFTWARE -> {
                candidate.isQualified = true
                require(candidate.isQualified) {
                    println("Candidate could not answer well")
                }
                next?.handleInterview(candidate, job)
            }

            Domain.MANAGERIAL -> {
                candidate.isQualified = false
                require(candidate.isQualified) {
                    println("Candidate could not answer well")
                }
                next?.handleInterview(candidate, job)
            }
        }
        after()
    }

    override fun after() {
        println("TechnicalRound passed")
    }
}


class ManagerialRound(
    private val next: InterviewHandler?
) :
    InterviewHandler {
    override fun handleInterview(
        candidate: Candidate,
        job: Job,
    ) {
        when (job.domain) {
            Domain.SOFTWARE -> {
                candidate.isQualified = true
                require(candidate.isQualified) {
                    println("Candidate could not answer well")
                }
                next?.handleInterview(candidate, job)
            }

            Domain.MANAGERIAL -> {
                candidate.isQualified = true
                require(candidate.isQualified) {
                    println("Candidate could not answer well")
                }
                next?.handleInterview(candidate, job)
            }
        }
        after()
    }

    override fun after() {
        println("ManagerialRound passed")
    }
}


private fun main() {
    val candidate = Candidate(
        qualification = "M.Tech",
        grade = 7.8f,
        technology = "Android",
        expertiseInDomain = ExpertiseInDomain.MODERATE,
        isQualified = false
    )

    val job = Job(
        domain = Domain.MANAGERIAL,
        ctcRange = 25000..35000,
        expertiseNeeded = ExpertiseInDomain.BEGINNER
    )

    val interviewChain = ScreeningRound(TechnicalRound(ManagerialRound(null)))
    interviewChain.handleInterview(candidate, job)
}
