# 🧱 Arkanoid Game – Java OOP Implementation

A fully object-oriented implementation of the classic **Arkanoid** arcade game, developed in Java with a strong focus on clean architecture, modular design, and event-driven logic.

This project demonstrates structured problem-solving, separation of concerns, and maintainable game design principles.

---

## 🎮 Gameplay Overview

Control the paddle, bounce the ball, and destroy all blocks to clear the level.

- The ball bounces off walls and the paddle  
- Blocks disappear upon collision  
- Score and remaining lives are tracked  
- The game ends when all blocks are cleared or lives reach zero  

Classic mechanics. Clean implementation. Solid architecture.

---

## 🏗 Architecture & Design

The game is structured using clear object-oriented principles and separation of responsibilities.

### Core Abstractions

- **Sprite** – Renderable objects in the game
- **Collidable** – Objects that participate in collision detection
- **GameEnvironment** – Manages collision logic
- **GameLevel** – Controls game flow and state

### Key Components

- `Counter`  
  Tracks dynamic values such as score, lives, and remaining blocks.

- `BlockRemover`  
  Listens for block collisions and removes blocks while updating the counter.

- `BallRemover`  
  Removes balls that leave the screen and updates life tracking.

- Collision Listener System  
  Enables event-driven responses instead of hard-coded logic.

This modular structure ensures that rendering, physics, and game rules remain cleanly separated.

---

## 🧠 Technical Highlights

- Pure Java implementation  
- Object-Oriented Design (OOP)  
- Event-driven collision handling  
- Encapsulation of game state  
- Clear separation between logic and rendering  
- Apache Ant build configuration  

The project emphasizes maintainability, extensibility, and clean architectural decisions over quick fixes.

---

## 📂 Project Structure
src/
game/
sprites/
collisions/
listeners/

build.xml
README.md
.gitignore

---

## 🛠 How to Run

### Using Apache Ant:

```bash
ant compile
ant run

Or manually:
javac -d bin src/**/*.java
java -cp bin Game

🧠 Lessons Learned

Building this Arkanoid implementation was not just about recreating a classic game — it was an exercise in designing clean, maintainable systems.

1️⃣ Separation of Responsibilities

Mixing collision logic, rendering, and state updates leads to tightly coupled code.
Introducing dedicated components like BlockRemover, BallRemover, and Counter reinforced the importance of single responsibility and modular architecture.

2️⃣ Event-Driven Design Improves Scalability

Using collision listeners instead of hard-coded behavior made the system:
Easier to extend
Easier to debug
More flexible
New behaviors can be added without modifying core game objects.

3️⃣ Abstraction Enables Clean Expansion

Separating Sprite, Collidable, and game environment logic created clear architectural boundaries.
This allows future features (power-ups, multiple levels, new block types) to be added without restructuring the entire system.

4️⃣ State Management in Interactive Systems

Tracking score, lives, and remaining blocks through Counter objects highlighted the importance of centralized state handling in game development.
Improper state control leads to unpredictable behavior — structured counters prevent that.

5️⃣ Architecture Over Short-Term Convenience

During development, quick fixes were tempting. Refactoring toward cleaner abstractions significantly improved readability and scalability.
The project reinforced the long-term value of thoughtful system design.

🚀 Possible Future Improvements

Multiple levels with increasing difficulty
Power-ups and special blocks
Sound effects and animations
Start menu and end-game screens
Refactoring into a more advanced game engine structure

👩🏻‍💻 Author

Developed as part of a Computer Science coursework project with a focus on:
Clean Object-Oriented Design
Event-Driven Systems
Game Loop Architecture
Maintainable Code Structure
