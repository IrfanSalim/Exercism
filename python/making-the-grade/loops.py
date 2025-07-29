def round_scores(student_scores: list) -> list:
    """
    :param student_scores: list of student exam scores as float or int.
    :return: list of student scores *rounded* to nearest integer value.
    """
    return list(map(round, student_scores))


def count_failed_students(student_scores: list) -> int:
    """
    :param student_scores: list of integer student scores.
    :return: integer count of student scores at or below 40.
    """
    return sum(map(lambda n: n <= 40, student_scores))


def above_threshold(student_scores: list, threshold: int):
    """
    :param student_scores: list of integer scores
    :param threshold :  integer
    :return: list of integer scores that are at or above the "best" threshold.
    """
    return list(filter(lambda n: n >= threshold, student_scores))


def letter_grades(highest):
    """
    :param highest: integer of highest exam score.
    :return: list of integer lower threshold scores for each D-A letter grade interval.
             For example, where the highest score is 100, and failing is <= 40,
             The result would be [41, 56, 71, 86]:

             41 <= "D" <= 55
             56 <= "C" <= 70
             71 <= "B" <= 85
             86 <= "A" <= 100
    """
    return list(map(lambda n: n * (highest - 40) // 4 + 41, [0, 1, 2, 3]))


def student_ranking(student_scores: list, student_names: list) -> list:
    """
    :param student_scores: list of scores in descending order.
    :param student_names: list of names in descending order by exam score.
    :return: list of strings in format ["<rank>. <student name>: <score>"].
    """
    return list(map(
        lambda score: f"{score[0] + 1}. {score[1][1]}: {score[1][0]}",
        enumerate(reversed(sorted(zip(student_scores, student_names))))))


def perfect_score(student_info: list) -> list:
    """
    :param student_info: list of [<student name>, <score>] lists
    :return: first `[<student name>, 100]` or `[]` if no student score of 100 is found.
    """
    return next(filter(lambda info: info[1] == 100, student_info), [])