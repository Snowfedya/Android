# Validate Lab Compliance

## Task: /project:validate-lab <lab_number>

**Validation Steps**:
1. Check technology whitelist/blacklist from CLAUDE.md
2. Scan for forbidden imports (Fragment in Lab 1, Room in Lab 5, etc.)
3. Verify AndroidManifest has correct Activity/permissions
4. Check build.gradle dependencies match lab requirements
5. Run `./gradlew assembleDebug` to ensure compilation
6. Generate compliance report in markdown format

**Arguments**: `$ARGUMENTS` = lab number (1-7)

**Output**: 
- ✅ Compliant technologies
- ❌ Violations found
- ⚠️ Warnings (optional features)