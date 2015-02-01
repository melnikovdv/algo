#Yavor Ilija
#July 28, 2013
#Counting Inversions

def inversions(array):
    if len(array) <= 1:
        return 0
    middle = int(len(array) / 2)
    left = array[:middle]
    right = array[middle:]
    
    a = inversions(left)
    b = inversions(right)
    c = split(left, right)

    return a + b + c

def split(left, right):
    count = 0
    i, j = 0, 0
    lleft = len(left)
    lright = len(right)
    while i < lleft and j < lright:
        if left[i] <= right[j]:
            i += 1
        else:
            count += lleft - i
            j += 1
            
    return count

array = [1, 3, 5, 2, 4, 6]
print(inversions(array))

array = [1, 5, 3, 2, 4]
print(inversions(array))

array = [5, 4, 3, 2, 1]
print(inversions(array))

array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
print("wrong: " + str(inversions(array)))

array = [1, 3, 5, 2, 4, 6, 1]
print("wrong: " + str(inversions(array)))

