from django.db import models
from django.contrib.auth.models import AbstractUser
from model_utils import Choices
# Create your models here.
class User(AbstractUser):
    name = models.CharField(max_length=100)
    email = models.EmailField(max_length=100, unique=True)
    password = models.CharField(max_length=255)
    
class Task(models.Model):

    STATUS = Choices(
        ('Completed', 'Completed'),
        ('InProgress', 'In Progress'),
        ('NotStarted', 'Not Started')
    )
    
    def __init__(self, name, priority, status, **kwargs):
    
        self.name = name
        self.priority = priority
        self.status = status
    
    name = models.CharField(max_length=100)
    priority = models.CharField(max_length=150)
    status = models.CharField(max_length=150, choices=STATUS, default=STATUS.NotStarted)
    user =  models.ForeignKey(User, on_delete=models.CASCADE, related_name='tasks')
    
