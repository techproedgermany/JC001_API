package utils;

public class HamcrestMatchersMethods {

        /*
    Core Matchers:

    is(value):            Matches if the examined object is equal to the specified value.
    equalTo(value):       Same as is(value).
    not(value):           Matches if the examined object is not equal to the specified value.
    nullValue():          Matches if the examined object is null.
    notNullValue():       Matches if the examined object is not null.

    Logical Matchers:

    allOf(matcher1, matcher2, ...):     Matches if all of the specified matchers match.
    anyOf(matcher1, matcher2, ...):     Matches if any of the specified matchers match.
    not(matcher):                       Matches if the specified matcher does not match.

    Text Matchers:

    containsString(substring):          Matches if the examined string contains the specified substring.
    startsWith(prefix):                 Matches if the examined string starts with the specified prefix.
    endsWith(suffix):                   Matches if the examined string ends with the specified suffix.

    Number Matchers:

    closeTo(expected, delta):           Matches if the examined value is within a specified delta of the expected value.
    greaterThan(value), greaterThanOrEqualTo(value): Matches if the examined value is greater than or equal to the specified value.
    lessThan(value), lessThanOrEqualTo(value):       Matches if the examined value is less than or equal to the specified value.

    Collection Matchers:

    hasItem(value):                 Matches if the examined iterable has at least one item that is equal to the specified value.
    hasItems(value1, value2, ...):  Matches if the examined iterable has at least one item that matches each specified value.
    hasSize(matcher):               Checks size of matchers in the collection


    Object Matchers:

    equalToIgnoringCase(string):    Matches if the examined string is equal to the specified string, ignoring case.
    sameInstance(expected):         Matches if the examined object is the same instance as the specified object.
    hasProperty(propertyName):      Matches if the examined object has a JavaBean property with the specified name.

        These are just some of the commonly used Hamcrest matchers and methods. Hamcrest provides a rich set of matchers, allowing you to create expressive and readable assertions in your tests.

     */
}
