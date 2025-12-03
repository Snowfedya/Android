# Setup Lab Environment Strategy

## Objective
Initialize a new lab environment respecting the **cumulative directory structure**.

## Algorithm for Agent

1.  **Identify Context**:
    *   Current Lab: `N` (e.g., 3)
    *   Target Lab: `N+1` (e.g., 4)

2.  **Git Operations**:
    *   `git checkout lab-<N>`
    *   `git checkout -b lab-<N+1>`

3.  **FileSystem Operations**:
    *   *Critical Step*: Copy the previous lab's project to a new folder.
    *   Command: `cp -r lab-<N> lab-<N+1>`
    *   *Clean up*: Delete `build/` and `.gradle/` folders inside the new `lab-<N+1>` directory to avoid cache conflicts.

4.  **Configuration**:
    *   Update `lab-<N+1>/app/build.gradle` to add new dependencies required for Lab `N+1` (e.g., Navigation Component).
    *   Remove forbidden technologies if any "future code" accidentally leaked in.

5.  **Documentation**:
    *   Update root `tasks.md` marking the new lab as "In Progress".
    *   Update root `README.md` marking the new lab status.

6.  **Commit**:
    *   `git add .`
    *   `git commit -m "chore: setup structure for lab-<N+1>"`