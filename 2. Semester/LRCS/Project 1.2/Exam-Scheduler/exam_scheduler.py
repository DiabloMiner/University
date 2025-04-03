from pandas import DataFrame
###################################################################################################
#                                      Helper functions                                           #
###################################################################################################

var2num = dict({})
num2var = dict({})

def v2n(i, j, k):
    return var2num[(i, j, k)]

def n2v(i):
    return num2var[i]

def all_events_with_same_course(course, days: list[str], rooms: list[str]) -> list:
    l = list([])
    for i in days:
        for j in rooms:
            l.append((course, i, j))
    return l

def get_all_2_subsets(list) -> list[list]:
    subsets = []
    for i in range(0, len(list)):
        for j in range(0, len(list)):
            subsets.append([list[i], list[j]])
    return subsets


def get_all_2_subsets_2(list1, list2) -> list[list]:
    subsets = []
    for i in range(0, len(list1)):
        for j in range(0, len(list2)):
            subsets.append([list1[i], list2[j]])
    return subsets

def get_all_different_2_subsets(list) -> list[list]:
    subsets = []
    for i in range(0, len(list)):
        for j in range(i + 1, len(list)):
            subsets.append(list[i], list[j])
    return subsets

def get_all_different_2_subsets(list1, list2) -> list[list]:
    subsets = []
    for i in range(0, len(list1)):
        for j in range(0, len(list2)):
            subsets.append([list1[i], list2[j]])
    return subsets

###################################################################################################
#                                      Main functions                                             #
###################################################################################################
'''
Encoding:
### A SHORT ENCODING DESCRIPTION HERE ###
'''
def encode_cnf(days: list[str],
               courses: list[str],
               rooms: DataFrame,
               students_courses: DataFrame):
    '''
    Encode the exam scheduling problem as a CNF formula
    Given a list of time slots, rooms, courses, and students_course, returns a CNF formula that encodes that
    - each course is scheduled on exactly one day
    - each course is scheduled in exactly one room
    - each day and room should have at most one course
    - each student should have at most one course per day

    input:
    - days: a list of days to schedule the exams
    - courses: a list of courses
    - rooms: a DataFrame with columns 'Room' and 'Capacity'
    - students_course: a DataFrame with columns 'Student' and 'Courses' containing the list of courses each student attends.

    output:
    - cnf: a list of clauses
        A clause is a list of literals.
        A literal is a positive or negative integer representing respectively a variable or its negation.
    '''
    cnf: list[list[int]] = []
    ### YOUR CODE HERE ###

    # determine the capacities each room needs
    needed_capacities = list()
    # initialize list
    for i in courses:
        needed_capacities.append(0)
    # actually fill list
    for i in enumerate(courses):
        for j in students_courses["Courses"]:
            if (i[1] in j):
                needed_capacities[i[0]] += 1
    
    # define the rooms each exam can use based on the needed capacities
    room_dict = dict()
    for i in enumerate(courses):
        for j in range(0, len(rooms)):
            name = list(rooms["Room"])[j]
            capacity = list(rooms["Capacity"])[j]
            if capacity >= needed_capacities[i[0]]:
                values = list()
                if (i[1] in room_dict.keys()):
                    values = [g for g in room_dict[i[1]]]
                values.append(name)

                room_dict.update({i[1]: values})

    # Encode each variable as a number
    # (Encode only those possible rooms that actually have proper capacity)
    g = 1
    for i in courses:
        for j in days:
            for k in room_dict[i]:
                var2num.update({(i, j, k): g})
                num2var.update({g: (i, j, k)})
                g += 1

    # Each exam has to happen
    # i.e. one variant of each exam has to be happen
    for i in courses:
        l = list()
        for j in days:
            for k in room_dict[i]:
                l.append(v2n(i, j, k))
        cnf.append(l)

    # Each exam should only happen exactly once
    # that means if a exam happens that implies all other variants of that exam do not happen
    # (Room might also be able to be removed from this loop) (this also includes other room variants)
    for i in courses:
        #iEvents = list(allEventsWithSameCourse(i, days, rooms["Room"]))
        for j in enumerate(days):
            for k in enumerate(room_dict[i]):
                current_event = (i, j[1], k[1])
                # iEvents.remove(currentEvent)
                # for otherEvent in iEvents:
                    # cnf.append([-v2n(currentEvent[0], currentEvent[1], currentEvent[2]), -v2n(otherEvent[0], otherEvent[1], otherEvent[2])])
                for g in range(j[0] + 1, len(days)):
                    for h in range(k[0] + 1, len(room_dict[i])):
                        other_event = (i, days[g], room_dict[i][h])
                        cnf.append([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])])

    print(len(cnf))

    # Two exams cannot happen on the same day k in the same room g
    for i in enumerate(courses):
        for k in days:
            for g in room_dict[i[1]]:
                other_courses = list(courses)
                other_courses.remove(i[1])
                for j in range(i[0] + 1, len(courses)):
                    # check if they can even be in the same room because of capacity
                    if (g in room_dict[courses[j]]):
                        current_event = (i[1], k, g)
                        other_event = (courses[j], k, g)
                        cnf.append([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])])

    
    print(len(cnf))

    # TODO: Exams on different days are banned by method even though they should be valid, also falsely evaluated
    # TODO: maybe make sure that the clauses above do not affect the ones below

    # print(len(cnf))
    # If a student has an exam on a day that implies that no other exam that he has should be on that day
    course_subsets = list()
    room_list = list(rooms["Room"])
    course_list = list(students_courses["Courses"])
    for i in range(0, len(course_list)):
        i_list = list(course_list[i])
        for j in range(0, len(i_list)):
            for k in range(j + 1, len(i_list)):
                to_be_added = [i_list[j], i_list[k]]
                to_be_added_inverse = [i_list[k], i_list[j]]
                if ((not to_be_added in course_subsets) and (not to_be_added_inverse in course_subsets)):
                    course_subsets.append(to_be_added)

    combinations = get_all_different_2_subsets(course_subsets, room_list)
    # replace room_comb with fitting rooms for each exam
    # room_comb = get_all_2_subsets(room_list)
    for k in range(0, len(days)):
        for i in range(0, len(course_subsets)):
            for [g, h] in get_all_2_subsets_2(room_dict[course_subsets[i][0]], room_dict[course_subsets[i][1]]):
                event1 = (course_subsets[i][0], days[k], g)
                event2 = (course_subsets[i][1], days[k], h)
                if (not g == h):
                    cnf.append([-v2n(event1[0], event1[1], event1[2]), -v2n(event2[0], event2[1], event2[2])])
    #         print(len(cnf))
    #         for i in enumerate(cnf):
    #             element1 = set(i[1])
    #             for j in enumerate(cnf):
    #                 element2 = set(j[1])
    #                 if (element1 == element2 and i != j):
    #                     print(str(element1) + " | " + str(i) + " | " + str(j))
    # for k in range(0, len(days)):
    #     for i in range(0, len(course_subsets)):
    #         event1 = combinations[i]
    #         cnf.append([-v2n(event1[0][0], days[k], event1[1]), -v2n(event1[0][1], days[k], event1[1])])



    # for i in course_subsets:
    #     print(len(cnf))
    #     combinations = get_all_different_2_subsets(i, room_list)
    #     for g in range(0, len(days)):
    #         for j in range(0, len(combinations)):
    #             for k in range(j + 1, len(combinations)):
    #                 event1 = combinations[j]
    #                 event2 = combinations[k]
    #                 cnf.append([-v2n(event1[0], days[g], event1[1]), -v2n(event2[0], days[g], event2[1])])
    
    # for i in enumerate(cnf):
    #     element1 = set(i[1])
    #     for j in enumerate(cnf):
    #         element2 = set(j[1])
    #         if (element1 == element2 and i != j):
    #             print(str(element1) + " | " + str(i) + " | " + str(j))


    for i in enumerate(students_courses["Student"]):
        # define all possible subsets of students courses that could be taken / that are taken
        room_list = list(rooms["Room"])
        one_student_courses = list(students_courses["Courses"][i[0]])
        # combinations = get_all_different_2_subsets(one_student_courses, days, room_list)

        # find all different two subsets of courses that are used
        
        # determine all possible subsets for these subsets

        # for j in range(0, len(combinations)):
        #     for k in range(j + 1, len(combinations)):
        #         event1 = combinations[j]
        #         event2 = combinations[k]
        #         if ((not ([-v2n(event1[0], event1[1], event1[2]), -v2n(event2[0], event2[1], event2[2])] in cnf)) and (not ([-v2n(event2[0], event2[1], event2[2]), -v2n(event1[0], event1[1], event1[2])] in cnf))):
        #             cnf.append([-v2n(event1[0], event1[1], event1[2]), -v2n(event2[0], event2[1], event2[2])])
        
        # for i in enumerate(cnf):
        #     element1 = set(i[1])
        #     for j in enumerate(cnf):
        #         element2 = set(j[1])
        #         if (element1 == element2 and i != j):
        #             print(str(element1) + " | " + str(i) + " | " + str(j))
        # print("done \n\n\n")
        # for [j, h] in get_all_different_2_subsets(one_student_courses):
            # for [g, m] in get_all_different_2_subsets(room_list):
                # for k in days:
                    # current_event = (j, k, g)
                    # other_event = (h, k, m)
                    # cnf.append([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])])
        # for j in enumerate(one_student_courses):
        #     for k in enumerate(days):
        #         for g in enumerate(rooms["Room"]):
        #             # if a student has exam j on day k in room g
        #             current_event = (j[1], k[1], g[1])
        #             other_courses = list(students_courses["Courses"][i[0]])
        #             other_courses.remove(j[1])
        #             for h in other_courses:
        #                 for m in rooms["Room"]:
        #                     # there cannot be another exam that he has h happening on the same day k in some room m
        #                     other_event = (h, k[1], m)
        #                     if ((not ([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])] in cnf)) and (not ([-v2n(other_event[0], other_event[1], other_event[2]), -v2n(current_event[0], current_event[1], current_event[2])] in cnf))):
        #                         cnf.append([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])])
        #             # for h in range(k[0] + 1, len(one_student_courses)):
        #             #     for m in range(g[0] + 1, len(rooms["Room"])):
        #             #         # there cannot be another exam h happening on the same day k in some room m
        #             #         other_event = (one_student_courses[h], k[1], rooms["Room"][m])
        #             #         cnf.append([-v2n(current_event[0], current_event[1], current_event[2]), -v2n(other_event[0], other_event[1], other_event[2])])


    # for i in enumerate(cnf):
    #     element1 = set(i[1])
    #     for j in enumerate(cnf):
    #         element2 = set(j[1])
    #         if (element1 == element2 and i != j):
    #             print(str(element1) + " | " + str(i) + " | " + str(j))

    return cnf

def decode_model(model: list[int],
                 days: list[str],
                 courses: list[str],
                 rooms: DataFrame,
                 students_course: DataFrame):
    '''
    Given a model, returns the schedule as a DataFrame
    The schedule is a DataFrame with columns 'Course', 'Day', and 'Room'

    input:
    - model: the model returned by the SAT solver
        The model is a list of literals
        Literals are positive or negative integers representing respectively a variable or its negation.
    - days: a list of days to schedule the exams
    - rooms: a DataFrame with columns 'Room' and 'Capacity'
    - course_list: a list of courses
    - students_course: a DataFrame with columns 'Student' and 'Courses'

    Note:
    It is possible that not all inputs are used in the function.

    output:
    - schedule: a DataFrame with columns 'Course', 'Day', and 'Room'
    '''
    schedule = DataFrame(columns=['Course', 'Day', 'Room'])
    ### YOUR CODE HERE ###

    k = 1
    for lit in model:
        if lit >= 0:
            temp = n2v(lit)
            schedule.loc[k] = [temp[0], temp[1], temp[2]]
            k += 1


    return schedule
