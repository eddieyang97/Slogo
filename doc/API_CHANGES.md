# API Changes

## Backend
- The execute() method in the CodeInterpreter now takes a String array instead of a String. This allows for comments to be recognized and removed from the input before parsing.
- The setProperties() method in the CodeIntepreter now takes a String instead of a File. This allows the resource to be read as a ResourceBundle instead of a File.