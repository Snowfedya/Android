# Downgrade Lab to Specification

## Task: /project:downgrade-lab <lab_number>

**Purpose**: Remove over-engineered code not yet taught

**Actions**:
1. Identify technologies from future labs in current code
2. Create backup branch before destructive changes
3. Remove forbidden dependencies from build.gradle
4. Delete forbidden files (Fragments in Lab 1, nav_graph in Lab 3)
5. Replace advanced patterns with simple implementations
6. Preserve all UI layouts and resources
7. Test application still builds and runs

**Arguments**: `$ARGUMENTS` = lab number (1-7)

**Example**: For Lab 1, remove all Fragment code and convert to Activities