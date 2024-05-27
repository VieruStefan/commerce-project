from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from models import user, listing

SQLALCHEMY_DATABASE_URL = "sqlite:///./sql_app.db"
# SQLALCHEMY_DATABASE_URL = "postgresql://user:password@postgresserver/db"

engine = create_engine(
    SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False}
)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()
Base.metadata.create_all(bind=engine)

# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


def get_user(user_id: int):
    return db.query(user.UserBase).filter(user.UserBase.id == user_id).first()


def get_user_by_email(email: str):
    return db.query(user.UserBase).filter(user.UserBase.email == email).first()


def get_users(skip: int = 0, limit: int = 100):
    return db.query(user.UserBase).offset(skip).limit(limit).all()


def create_user(userRegister: user.UserRegister):
    db = get_db()
    db_user = user.UserBase(username=userRegister.username, email=userRegister.email)
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user