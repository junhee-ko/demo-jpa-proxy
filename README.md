## Intro

Test for JPA 

1. loading : eager / lazy
2. cascade
3. orphan removal

## Loading

- If fetch type is eager, team is also selected with member.
- If fetch type is lazy, team is not selected but member is selected.

## Cascade

- If parent class is saved with cascade, then child class is also saved.

## Orphan removal

- If child class is removed in collection in parent class, then child class is also removed in DB table.
